package org.queiroz.themoviedb.di.scopes

import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FILE,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
annotation class ChildFragmentScoped
