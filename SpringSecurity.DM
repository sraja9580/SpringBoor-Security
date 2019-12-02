						Spring Boot Security
											
What is Authentication
	Validating user,ie make sure the client accessing the resource valid user
	
Who does the Authentication in Boot
	AuthenticationManager->authenticate() method does the authentication
	
Who does the Authorization 
	AuthenticationManager does it.
	
How Spring Web Security Works?

![alt text](https://raw.github.com/sraja9580/SpringBoor-Security/blob/master/How-Authendication-Works-Step1.PNG)
	
1. Spring Boot Configure Authentication 01_spring_boot_authentication
	https://www.youtube.com/watch?v=iyXne7dIn7U
	
	Use Spring Provided Authentication
	
		1. Create Spring Boot Project With Starter Web and Starter Security
		2. Run the application you will get login screen login - You will be able to login
			user: user ,password: you will get it during deployment
			Using generated security password: 812d0dde-1131-46de-9ab0-f522b96f2fc3 (this will change on every deployment)
			http://localhost:8081/
			
	Custom user and password
		
		if required you can configure custom user and password in propertied
			spring.security.user.name=raja
			spring.security.user.password=test

		1. Configure credentials
		2. Restart the application and access it using credentials configured
			you will be able to access it
			http://localhost:8081/
		
	Custom Authentication:
		*Spring default authenticate has limitation has limitation, we can only have one use.
		We can provide custom authentication
			by extend WebSecurityConfigurerAdapter
			and over ride protected void configure(AuthenticationManagerBuilder auth)
		* add @EnableWebSecurity in security class, you can add it in boot main class also in that case w have 
		  annotate security class with @Configuration

2. Spring Boot Configure Authorization 02_spring_boot_authorization
	https://www.youtube.com/watch?v=payxWrmF_0k
	
    * Authorization verify whether user(authenticated) has access to particular resource.
	
	* Let us to the following, create service with below 3 endpoints
		/ Every one can access (even if not logged in also)
		/user user and admin roles can access
		/admin only admin can access
		
	*BY DEFAULT AuthenticationManager AUTHROZIES ALL REQUEST,IT JUST CHECKS WHETHER USER LOGGED IN OR NOT,
	 IT DONT PROVIDE ROLE BASED AUTHORIZATION
	 
	* We can provide Customized Authentication 
		over riding configure(HttpSecurity httpSecurity)of WebSecurityConfigurerAdapter
		
	1. Create Spring Boot Project With Starter Web and Starter Security
	2. Create Resource class with 3 endpoints / ,/user,/admin
	3. Create Custom Security class extend WebSecurityConfigurerAdapter
		over ride  configure(HttpSecurity httpSecurity)
		protected void configure(HttpSecurity httpSecurity) throws Exception  {
		httpSecurity.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER","ADMINS")
		.antMatchers("/").permitAll()
		.and().formLogin();
		}
	4. while authorizing we have to start with most restricted url to list restricted.
	   in this example if put .antMatchers("/").permitAll() first other two items will not be verified,
	   every one will access everything so we have to be careful with authentication order
	   
	5.test
		http://localhost:8081/ --access with out login
		http://localhost:8081/user --only logged in users with user and admin role
		http://localhost:8081/admin --only logged in users with admin role
