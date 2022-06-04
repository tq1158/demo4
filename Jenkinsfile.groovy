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
        emailext compressLog: true,
        attachLog: true,
        recipientProviders: [culprint(),developers(),requestors(),brokenBuildSuspects()],
        to: '2310307125@qq.com'
      }
  }
}