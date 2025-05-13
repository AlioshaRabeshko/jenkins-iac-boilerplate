import jenkins.model.*
import hudson.model.*
import javaposse.jobdsl.plugin.ExecuteDslScripts


println "--> Creating seed job"

def instance = Jenkins.getInstance()

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
}
