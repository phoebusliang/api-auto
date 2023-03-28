def cleanUp(folder, file) {
    sh '''
        if [ -d folder ]; then
            rm -rf folder
        fi

        if [ -e file]; then
            rm file
        fi

        list test
    '''
}

return this
