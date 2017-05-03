package org.sample

class RegistrationController {

    static scaffold = Registration


    // from template:
//    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond ${className}.list(params), model:[${propertyName}Count: ${className}.count()]
//    }

    def filtering(Integer max) {
//        <td><g:link action="show" id="${bean.id}">${fieldValue(bean: bean, field: "id")}</g:link></td>
//        <td>${fieldValue(bean: bean, field: "race")}</td>
//        <td>${fieldValue(bean: bean, field: "runner")}</td>
//        <td>${fieldValue(bean: bean, field: "paid")}</td>
//        <td><g:formatDate date="${bean.dateCreated}" /></td>

        def raceP = params.race
        def runnerP = params.runner
        String paidP = params.paid
        def dateCreated = params.dateCreated
        println("filtering: params=" + params)

        // we save the original values to be displayed again at UI re-rendering
        // (since we are not doing AJAX but a classic HTTP request)
        def filter = ['race':raceP,'runner':runnerP,'paid':paidP, 'dateCreated':dateCreated]

        // params preparation
        def paramsWithoutPosition = [:]
        int newMax = 10
        int offset = 0
        params.each {
            if (it.key == "offset"){
                offset = it.value.toInteger()
            } else if (it.key == "max"){
                newMax = it.value.toInteger()
            } else {
                paramsWithoutPosition.put(it.key, it.value)
            }
        }

        def sortColumn = paramsWithoutPosition.sort
        println("filtering: sort=" + sortColumn)
        println("filtering: race=" + raceP)
        println("filtering: runner=" + runnerP)
        println("filtering: paid=" + paidP)
        println("filtering: dateCreated=" + dateCreated)

        // basic query
//        def lst = createCriteria().list(paramsWithoutPosition) {  //ignore for now
        def c = Registration.createCriteria()
        def lst = c.list {
            if(raceP?.trim()) {
                race {
                    ilike("name",     '%'+raceP+'%')
                }
            }
            if(runnerP?.trim()) {
                runner {
                    or {
                        ilike("firstName",     '%'+runnerP+'%')
                        ilike("lastName",     '%'+runnerP+'%')
                    }
                }
            }
            if(paidP?.trim()) {
                if ('true'.contains(paidP.toLowerCase())) {
                    eq('paid', true)
                } else if ('false'.contains(paidP.toLowerCase())) {
                    eq('paid', false)
                }
            }
            if(dateCreated?.trim())   { ilike("dateCreated",        '%'+dateCreated+'%')}
        }

        params.max = newMax
        respond lst, model:[registrationInstanceCount: lst.size(), filter:filter]
    }
}
