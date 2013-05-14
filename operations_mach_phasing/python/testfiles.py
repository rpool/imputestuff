def main():
    numChunks = [ 45, 47, 38, 35, 36, 39, 31, 31, 27, 30, 28, 28, 22, 18, 16, 17, 13, 17, 9, 14, 8, 8 ]
    for i in range(1, 23):
        for j in range(1, 11):
            currentChunkSize = numChunks[(i - 1)]
            for k in range(1, currentChunkSize + 1):
                filePrefix = "chr" + str(i) + "_set" + str(j) + "_chunk" + str(k)
                result = True
                result = checkFile(filePrefix + ".haps.gz") and result
                result = checkFile(filePrefix + ".log") and result
                result = checkFile(filePrefix + ".minimac.erate.gz") and result
                result = checkFile(filePrefix + ".minimac.hapDose.gz") and result
                result = checkFile(filePrefix + ".minimac.haps.gz") and result
                result = checkFile(filePrefix + ".minimac.info.draft") and result
                result = checkFile(filePrefix + ".minimac.info.gz") and result
                result = checkFile(filePrefix + ".minimac.prob.gz") and result
                result = checkFile(filePrefix + ".minimac.rec.gz") and result
                result = checkFile(filePrefix + ".minimac.log") and result
                if result :
                    print "Chunk", i, j, k, "SUCCES"
                else :
                    print "Chunk", i, j, k, "FAIL"
                # print i, j, k
            
def checkFile(filename):
    try:
        open(filename)
        return True
    except IOError:
        print filename, "Does not exist!"
        return False

if  __name__ == '__main__':main()
