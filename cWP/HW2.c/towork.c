/*
 * hw2.c
 *
 *  Created on: 6 Dec 2018
 *      Author: elkana
 */


#define _GNU_SOURCE
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#define AMPER "&"

// arglist - a list of char* arguments (words) provided by the user
// it contains count+1 items, where the last item (arglist[count]) and *only* the last is NULL
// RETURNS - 1 if should cotinue, 0 otherwise
int process_arglist(int count, char** arglist);
int arr_copy(char **dest, char **src,int start,int end);
int find_the_pipe_sign(int count, char **arglist);


// prepare and finalize calls for initialization and destruction of anything required
int prepare(void);
int finalize(void);

int main(void)
{
	if (prepare() != 0)
		exit(-1);

	while (1)
	{
		char** arglist = NULL;
		char* line = NULL;
		size_t size;
		int count = 0;

		if (getline(&line, &size, stdin) == -1) {
			free(line);
			break;
		}

		arglist = (char**) malloc(sizeof(char*));
		if (arglist == NULL) {
			printf("malloc failed: %s\n", strerror(errno));
			exit(-1);
		}
		arglist[0] = strtok(line, " \t\n");

		while (arglist[count] != NULL) {
			++count;
			arglist = (char**) realloc(arglist, sizeof(char*) * (count + 1));
			if (arglist == NULL) {
				printf("realloc failed: %s\n", strerror(errno));
				exit(-1);
			}

			arglist[count] = strtok(NULL, " \t\n");
		}

		if (count != 0) {
			if (!process_arglist(count, arglist)) {
				free(line);
				free(arglist);
				break;
			}
		}

		free(line);
		free(arglist);
	}

	if (finalize() != 0)
		exit(-1);

	return 0;
}



int process_arglist(int count, char ** arglist){
	int c_pid, status, ampr=0, pip=0;


	//checking if  background process
	if (strcmp(arglist[count-1],AMPER)==0){
		ampr=1;
	}
	pip=find_the_pipe_sign(count, arglist);
	if((pip)){
		// create pipe and check success
		return pipe_case(count, arglist, pip);
	}else{
		printf("in parent ampr=%d\n", ampr);
		c_pid=fork();
		if(c_pid==0){
			//in child
			printf("in child\n");
			execvp(arglist[0], arglist );
			printf ("execvp f\n");
			return 1;
		}else if (c_pid<0){
			// fork failed
			printf("fork failed\n");
			return 1;
		}else{
			// in parent
			if (!(ampr)){
				while (-1!=wait(&status));
			}
			printf("parent end \n");
			return 1;
		}
	}
	return 1;
}

int prepare (void){
	return 0;
}

int finalize (void){
	return 0;
}


int pipe_case(int count, char **arglist, int ind){
	int i, c_pid1, c_pid2;
	int pfds[2];
	char **first_arg=NULL; // arg for the first program to be exac
	char **sec_arg=NULL;   // arg for the second program to be exec

	//pipe failed- return to callin function to ask next command
	if(pipe(pfds)<0){
		fprintf(stderr, "%s\n","pipe err");
		return 1;
	// pipe successed
	}else{
		first_arg=malloc((sizeof(char*))*(ind-1));
		sec_arg=malloc((sizeof(char*))*(count-ind-2));
		//malloc failed
		if((sec_arg==NULL) ||(first_arg==NULL)){
			fprintf(stderr,  "%s", "malloc failed\n");
			return 1;
		}
		//loading the programs names and argumnet to sublist for each program
		if (!arr_copy(first_arg, arglist, ind,0)){
			return 1;
		}
		if (!arr_copy(sec_arg, arglist,ind+1, count-1 )){
			return 1;
		}
		c_pid1=fork();
		if (c_pid1==0){
				//child process
				if (dup2(pfds[1],STDOUT_FILENO)<0){
					fprintf (stderr, "%s", "dup2 failed\n");
					return 1;
				}
				close(pfds[0]);
				close(pfds[1]);
				printf("%s", arglist[0]);
				execvp(first_arg[0],first_arg);
				printf("exec f\n");
				return 1;
			}
		else if(c_pid1>0){
				//parent process
				c_pid2=fork();
				if (c_pid2<0){
					printf("err forking\n");
					return 1;
				}else if (c_pid2==0){
					if(dup2(pfds[0],STDIN_FILENO)<0){
						printf("err dup2\n");
						return 1;
					}
					close(pfds[0]);
					close(pfds[1]);
					printf("sec2\n");
					execvp(sec_arg[0],sec_arg);
					printf("second failed\n");
					return 1;

				}else{
				// again in the father
					printf("in the parent after 2 forks\n");
					while(-1!=wait(&status));
					printf("parent ends\n");
				}
			}
			else{
				// first forking has failed
				fprintf(stderr ,"%s","err forking 1\n");
				return 1;
			}
		//end of father
		for (i=0; i<ind; i++){
			free(first_arg[i]);
		}
		for (i=0; i<(count-1-ind); i++){
			free(sec_arg[i]);
		}
		free(first_arg);
		free(sec_arg);
		return 1;
		}


}

//copy array of string src to array of string dest
int arr_copy(char **dest, char **src,int start,int end){
	int i;
	for (i=start; i<end; i++){
		dest[i]=malloc((sizeof(char))*(strlen(src[i])+1));
		if (dest[i]==NULL){
			fprintf(stderr,"%s", "malloc failed in arr_copyn" );
			return 0;
		}
		strcpy(dest[i], src[i]);
	}
	return 1;
}



//find the index of “|” if exist
int find_the_pipe_sign(int count, char **arglist){
	int i;
	for (i=0; i<count; i++){
		if (strcmp(arglist[i], "|")==0){
			break;
	}// we iterated over all the arsg and didnt find “|”
	if (i==(count-1)){
		return 0;
	// “|” in the i place
	}else{
		return i;
	}
}


}


