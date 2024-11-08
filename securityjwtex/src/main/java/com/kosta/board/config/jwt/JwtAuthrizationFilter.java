package com.kosta.board.config.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kosta.board.config.auth.PrincipalDetails;
import com.kosta.board.entity.User;
import com.kosta.board.repository.UserRepository;

//인가 : 로그인 처리가 되어야만 하는 처리가 들어왔을 때 실행
public class JwtAuthrizationFilter extends BasicAuthenticationFilter {

	@Autowired
	private UserRepository userRepository;
	
	private JwtToken jwtToken = new JwtToken();

	public JwtAuthrizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
		super(authenticationManager);
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String uri = request.getRequestURI();
		System.out.println("JwtAuthrizationFilter:"+uri);
		// 1. 로그인 (인증) 이 필요없는 요청은 그대로 진행
		if (!(uri.contains("/user") || uri.contains("/admin") || uri.contains("/manager"))) {
			chain.doFilter(request, response);
			return;
		}

		// 2. 토큰을 체크
		String authentication = request.getHeader(JwtProperties.HEADER_STRING);
		if (authentication == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요함");
			return;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> token = objectMapper.readValue(authentication, Map.class);
		System.out.println("token to Map : " + token);

		// 3. access Token : header로 부터 access Token을 가져와 check
		String accessToken = token.get("access_token");
		if (!accessToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요함");
			return;
		}

		// 빈문자열로 바꿈
		accessToken = accessToken.replace(JwtProperties.TOKEN_PREFIX, "");

		// 아이디 유저네임 가져와서 db에 있는지 체크
		try {
			// 1) Access Token
			// 1-1)보안키, 만료시간 check
			String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(accessToken)
					.getClaim("sub").asString();
			System.out.println("username" + username);

			// 1-2) username check
			if (username == null || username.equals(""))
				throw new Exception("사용자가 없음"); // 사용자가 없을 때 exception

			User user = userRepository.findByUsername(username);
			if (user == null)
				throw new Exception("사용자가 없음"); // 사용자가 DB에 없을때

			// 성공했다면
			// 1-3) User를 Authentication로 생성하여 Security Session에 넣어준다. (그러면 Controller에서 사용할
			// 수 있다)
			PrincipalDetails principalDetails = new PrincipalDetails(user);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principalDetails, null,
					principalDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);

			// 컨트롤러에 연동
			chain.doFilter(request, response);
			return;

		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 2) Refresh Token Check : Access Token invalidate일 경우
				// 실패했다면 로그인을 다시 하라고 시도
				String refreshToken = token.get("refresh_token");
				if (!refreshToken.startsWith(JwtProperties.TOKEN_PREFIX)) {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요함");
					return;
				}
				refreshToken = refreshToken.replace(JwtProperties.TOKEN_PREFIX, "");
				
				//2-1) 보안키, 만료시간 check
				String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
										.build().verify(refreshToken)
										.getClaim("sub").asString();
				System.out.println("username" + username);
				//2-2) username check
				if (username == null || username.equals("")) throw new Exception("사용자가 없음"); // 사용자가 DB에 없을때
				User user = userRepository.findByUsername(username);
				System.out.println(user);
				if (user == null) throw new Exception("사용자가 없음"); // 사용자가 DB에 없을때
				
				//accessToken, refreshToken 다시 만들어 보낸다.
				String reAccessToken = jwtToken.makeAccessToken(username);
				String reRefreshToken = jwtToken.makeRefreshToken(username);
				Map<String, String> map = new HashMap<>();
				map.put("access_token", JwtProperties.TOKEN_PREFIX+reAccessToken);
				map.put("refresh_token", JwtProperties.TOKEN_PREFIX+reRefreshToken);
				String reToken = objectMapper.writeValueAsString(map); //map -> jsonString
				response.addHeader(JwtProperties.HEADER_STRING, reToken);
				response.setContentType("application/json; charset=utf-8");
				response.getWriter().print("token");//토큰을 다시 줄거야 하는 나만의방법이야
				
			} catch (Exception e2) {
				e2.printStackTrace();
				//여기까지 왔다는 것은 1), 2) 둘다 실패했음 -> 다시해라
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 필요");
			}
		}

		super.doFilterInternal(request, response, chain);
	}

}
