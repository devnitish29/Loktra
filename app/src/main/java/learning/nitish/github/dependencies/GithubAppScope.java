package learning.nitish.github.dependencies;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Nitish Singh Rathore on 5/8/17.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface GithubAppScope {
}
