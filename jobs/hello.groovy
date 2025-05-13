job('hello-world') {
    description('Created with Job DSL')
    steps {
        shell('echo Hello from Job DSL!')
    }
}
