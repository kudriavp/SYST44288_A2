/*
SYST44288
October 5, 2018
Students: Pavel K, Herit G
Professor: Christina R.
*/

#include<stdio.h>
#include<sys/types.h>
#include<fcntl.h>
#include<string.h>
#include<stdlib.h>

int main(int argc, char *argv[])
{
    if( argc != 3 )
    {
        fprintf(stderr, "!!ERROR!!\nNot enough arguments\nPlease follow the format: filecopy 'input_txt' 'copy_txt'\n");
        exit(1);
    }
    //Variables
    int lines;
    int fileArr[2];
    char mem[100];
    pid_t cid;
    pipe(fileArr);

    int srcFile = open(argv[1], O_RDONLY);
    int trgFile = open(argv[2], O_RDWR);

    if (srcFile == -1) //If source file doesn't exist - throw an error and quit
    {
        fprintf(stderr, "!!ERROR!!\nFile '%s' does not exist!\n", argv[1]);
        exit(1);
    }
    else if (trgFile == -1) //If target file does not exist - create an empty file
    {
        FILE *file=fopen(argv[2],"w");
        fclose(file);
        trgFile = open(argv[2], O_RDWR);
    }

    cid = fork();
    if(cid == -1)
    {
	perror("Fork error. Exiting...");
	exit(1);
    }
    else if(cid == 0) //Child process
    {
        close(fileArr[1]); //Close output pipe

        read( fileArr[0], mem, sizeof(mem));

        write( trgFile, mem, strlen(mem)); //Write to the target file
    }
    else //Parent process
    {
        close(fileArr[0]); //Close input pipe

        while((lines = read(srcFile, mem, sizeof(mem)) > 0)) //Read from file and send it to the pipe
        {
            write(fileArr[1], mem, sizeof(mem));
        }
        close(fileArr[1]);
    }
}
