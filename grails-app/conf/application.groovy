
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.sample.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.sample.UserRole'
grails.plugin.springsecurity.authority.className = 'org.sample.Role'

grails.plugin.springsecurity.securityConfigType = 'Requestmap'
grails.plugin.springsecurity.requestMap.className = 'org.sample.Permission'

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

