#! grooy
pipeline{
  agent any

  stages{
      stage('deploy'){
        steps{
          ansiblePlaybook(
            playbook: "${env.WORKSPACE}/playbook.yml",
            inventory: "${env.WORKSPACE}/hosts"
          )
        }
      }
  }
}