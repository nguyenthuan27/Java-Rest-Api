package com.java6;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter  {
	@Autowired
	private JWTUtility jwtUtility;
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

//		String token = null;
//		String userName = null;
//		if (null != authorization && authorization.startsWith("Bearer ")) {
//			token = authorization.substring(7);
//			userName = jwtUtility.getUsernameFromToken(token);
//		}
//		if (userName != null) {
//			UserDetails userDetails = userService.loadUserByUsername(userName);
////			if (SecurityContextHolder.getContext().getAuthentication().getName()!= userName) {
//				if (jwtUtility.validateToken(token, userDetails)) {
//					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//							userDetails, null, userDetails.getAuthorities());
//					usernamePasswordAuthenticationToken
//							.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//				}
//			//}
//		}
//		System.out.println(SecurityContextHolder.getContext().getAuthentication());
//		filterChain.doFilter(httpServletRequest, httpServletResponse);
		String jwt = getJWT(httpServletRequest);

		try {
			if (StringUtils.hasText(jwt)) {
				String userId = jwtUtility.getUsernameFromToken(jwt);
				UserDetails userDetails = userService.loadUserByUsername(userId);
				if (userDetails != null) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			System.err.println("loi"+e);
		}
//		System.out.println(SecurityContextHolder.getContext().getAuthentication());
//		System.out.println(jwt+"a");
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	private String getJWT(HttpServletRequest httpServletRequest) {
		String authorization = httpServletRequest.getHeader("Authorization");
		if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
			return authorization.substring(7);
		}
		return null;
	}
}
