package org.sample

import org.hibernate.cfg.ImprovedNamingStrategy

/**
 * Created by A2036337 on 17.03.2017.
 */
class NamingStrategy extends ImprovedNamingStrategy {

    @Override
    String classToTableName(String className) {
        return "g3_" + super.classToTableName(className)
    }
}
