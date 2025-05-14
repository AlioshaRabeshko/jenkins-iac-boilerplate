job('git-poll-job') {
    description('Пулить master з Git кожну хвилину і запускає білд, якщо є зміни')

    scm {
        git {
            remote {
                url('https://github.com/AlioshaRabeshko/vix.git')
            }
            branch('*')
        }
    }

    triggers {
        scm('* * * * *') // every minute
    }

    steps {
        shell('npm install')
        shell('npm run lint')
    }
}
