#! grooy
pipeline{
  agent any

  stages{
      stage('deploy'){
        steps{
          ansiblePlaybook(
            playbook: "${env.WORKSPACE}/playbook.yml",
            inventory: "${env.WORKSPACE}/hosts",

          )
        }
      }
  }

  post{
      failure{
        emailext( 
        subject: "Status: ${currentBuild.result} - Job:'${env.JOB_NAME}'",
        body: "<p>Executed: Job(<b>'${env.JOB_NAME}:${env.BUILD_NUMBER}'</b>)</p><p>View console output at <a href='${env.BUILD_URL}'>${env.JOB_NAME}:${env.BUILD_NUMBER}</a></p><p><i>()Build log is attached.</i></p>", 
        compressLog: true,
        attachLog: true,
        recipientProviders: [culprits(),developers(),requestor(),brokenBuildSuspects()],
        to: '2310307125@qq.com'
        )
      }
  }
}