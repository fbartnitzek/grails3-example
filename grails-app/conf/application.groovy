

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.sample.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.sample.UserRole'
grails.plugin.springsecurity.authority.className = 'org.sample.Role'
//grails.plugin.springsecurity.requestMap.className = 'org.sample.Permission'
//grails.plugin.springsecurity.securityConfigType = 'Requestmap'
// seem to be ignored with permissions by: http://stackoverflow.com/questions/20668999/grails-spring-security-fails-to-present-the-login-page-due-to-a-redirect-loop
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/dbconsole',		 access: ['IS_AUTHENTICATED_ANONYMOUSLY']],
	[pattern: '/dbconsole/**',	 access: ['IS_AUTHENTICATED_ANONYMOUSLY']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/home/index',     access: ['permitAll']],
	[pattern: '/index_login.gsp',      access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

