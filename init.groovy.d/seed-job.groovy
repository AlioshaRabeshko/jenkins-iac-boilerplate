import jenkins.model.*
import hudson.model.*
import javaposse.jobdsl.plugin.ExecuteDslScripts
import javaposse.jobdsl.plugin.GlobalJobDslSecurityConfiguration
import jenkins.model.GlobalConfiguration

println "--> disabling scripts security for job dsl scripts"
GlobalConfiguration.all().get(GlobalJobDslSecurityConfiguration.class).useScriptSecurity=false

println "--> Creating seed job"
def instance = Jenkins.instance
def jobName = "seed-job"
def job = instance.getItem(jobName)
if (job == null) {
    def seedJob = instance.createProject(FreeStyleProject, jobName)

    def dslBuilder = new ExecuteDslScripts()
    dslBuilder.targets = "jobs/**/*.groovy"
    dslBuilder.useScriptText = false
    dslBuilder.removedJobAction = javaposse.jobdsl.plugin.RemovedJobAction.IGNORE
    dslBuilder.lookupStrategy = javaposse.jobdsl.plugin.LookupStrategy.JENKINS_ROOT
    dslBuilder.sandbox = false

    seedJob.buildersList.add(dslBuilder)
    seedJob.save()
    seedJob.scheduleBuild2(0)
} else {
    println "--> Seed job already exists, skipping creation"
}
