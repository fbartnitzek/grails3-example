

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.sample.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.sample.UserRole'
grails.plugin.springsecurity.authority.className = 'org.sample.Role'

//grails.plugin.springsecurity.securityConfigType = 'InterceptUrlMap'
grails.plugin.springsecurity.securityConfigType = 'Requestmap'
grails.plugin.springsecurity.requestMap.className = 'org.sample.Permission'

grails.plugin.springsecurity.interceptUrlMap = [
		[pattern: '/',               	access: ['permitAll']],
		[pattern: '/error',          	access: ['permitAll']],
		[pattern: '/index',          	access: ['permitAll']],
		[pattern: '/index.gsp',      	access: ['permitAll']],
		[pattern: '/index_login.gsp',	access: ['permitAll']],
		[pattern: '/shutdown',       	access: ['permitAll']],
		[pattern: '/assets/**',      	access: ['permitAll']],
		[pattern: '/**/js/**',       	access: ['permitAll']],
		[pattern: '/**/css/**',      	access: ['permitAll']],
		[pattern: '/**/images/**',   	access: ['permitAll']],
		[pattern: '/**/favicon.ico', 	access: ['permitAll']],

		[pattern: '/login/**',		 	access: ['permitAll']],
		[pattern: '/logout/**',		 	access: ['permitAll']],

		[pattern: '/home/**',        	access: ['permitAll']],
		[pattern: '/homeSec/**',        access: ['ROLE_ADMIN']],
		[pattern: '/dbconsole/**',	 	access: ['ROLE_ADMIN']],

		[pattern: '/race/**',        	access: ['ROLE_ADMIN']],
		[pattern: '/runner/**',      	access: ['ROLE_ADMIN']],
		[pattern: '/registration/**',	access: ['ROLE_ADMIN']],
		[pattern: '/user/**',      		access: ['ROLE_ADMIN']],
		[pattern: '/role/**',      		access: ['ROLE_ADMIN']],
		[pattern: '/userRole/**',      	access: ['ROLE_ADMIN']],
		[pattern: '/permission/**',     access: ['ROLE_ADMIN']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

