/*
 * stringarr.c
 *
 *  Created on: 6 Dec 2018
 *      Author: elkana
 */

#include <stdio.h>
#include  <stdlib.h>
#include <string.h>
int arr_copy(char **dest, char **src,int start,int end);

int main (){

	char *str[]={"echo ", "tr","|","trd", NULL};
	char ** fst=NULL;
	char **sec =NULL;
	int i;
	fst =malloc (sizeof(char*)*1);
	sec=malloc(sizeof(char)*2);
	arr_copy(fst, str, 0,2);
	arr_copy(sec, str,3,4);
	printf("%s  \n", fst[0]);
	for (int i=0; i<2; i++){
		printf("%s\n", sec[i]);
	}
	printf("start free\n");
	for (i=0; i<2 ;i++){
		printf("%s\n", fst[i]);
		free(fst[i]);

	}
	printf("start free 2\n");
		for (i=0; i<1 ;i++){
			printf("%s\n", sec[i]);
			free(sec[i]);

		}
	free(fst);
	free(Sec);
	printf("end"\n);


}



//copy array of string src to array of string dest
int arr_copy(char **dest, char **src,int start,int end){
	int i;
	for (i=0; i<end-start; i++){
		dest[i]=malloc((sizeof(char))*(strlen(src[i+start])+1));
		if (dest[i]==NULL){
			fprintf(stderr,"%s", "malloc failed in arr_copyn" );
			return 0;
		}
		strcpy(dest[i], src[i+start]);

	}
	return 1;
}
/*
 * try.c
 *
 *  Created on: 6 Dec 2018
 *      Author: elkana
 */


