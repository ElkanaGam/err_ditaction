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

	char *str[]={"tsr", "sec","trd", NULL};
	char ** fst=NULL;
	char **sec =NULL;
	fst =malloc (sizeof(char*)*1);
	sec=malloc(sizeof(char)*2);
	arr_copy(fst, str, 0,1);
	arr_copy(sec, str,1,3);
	printf("%s  ", fst[0]);



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
