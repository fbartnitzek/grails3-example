package org.sample

class RaceController {

    static scaffold = Race

    def csv() {}

    def savej() {
        Race race = new Race()
        bindData(race, params)

        try {
            race.validate()
        } catch (Exception e) {
            println "Validation threw an error! " + e.getLocalizedMessage()
            response.status = 500
            render(contentType: "application/json"){
                success false
                msg e.getLocalizedMessage()
            }
            return
        }

        if (race.hasErrors()) {
            response.status = 500
            def errors = race.errors.allErrors.collect {
                message(error:it, encodeAs:'HTML')
            }
            render(contentType: "application/json"){
                success false
                msg errors
            }
            return
        }

        try {
            race.save flush:true
        } catch (Exception e){
            println "Save threw an error! " + e.getMessage()
            response.status = 500
            render(contentType: "application/json") {
                success false
                msg e.getMessage()
            }
            return
        }

        render(contentType: "application/json"){
            success true
            msg race.toString() + " successfully saved!"
        }
    }
}
