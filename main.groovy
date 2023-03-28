def cleanUp(folder, file) {
    pirint (folder)
    pirint (file)
    sh '''
        if [ -d $folder ]; then
            rm -rf $folder
        fi

        if [ -e $file ]; then
            rm $file
        fi

        ls
        ls test
    '''
}

return this
