repository = 'https://github.com/jbharti/Jenkinsfile.git'
branch = 'master'
credentialsId = null
labelExpression = ''

stage 'Calculator Api'
def addsub, muldiv
fileLoader.withGit(repository, branch) {
    addsub = fileLoader.load('examples/fileLoader/addsub');
    muldiv = fileLoader.load('examples/fileLoader/muldiv');
}

stage 'Run Calculator'
addsub.add(10,5)
addsub.sub(10,5)
muldiv.mul(10,5)
muldiv.div(10,5)