#!/usr/bin/env groovy
library(
        identifier: "jenkins-share-library@master",
        retriever: modernSCM(
                [
                        $class       : "GitSCMSource",
                        remote       : "git@github.com:The1Central/jenkins-share-library.git",
                        credentialsId: "the1card_ssh"
                ]
        )
)

createMobilePipeline {
          gitUrl = "git@github.com:The1Central/common-rewards.git"
          githubRepo = "The1Central/common-rewards"
	  isLibrary = true
}
