def cleanUp(folder, file) {
    sh """
        echo ${folder}
        if [ -d ${folder} ]; then
            rm -rf ${folder}
        fi

        if [ -e ${file} ]; then
            rm ${file}
        fi

        ls
        ls test
    """
}

return this
