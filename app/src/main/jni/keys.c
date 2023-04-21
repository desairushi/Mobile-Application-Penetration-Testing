#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_example_myapplication_MainActivity_api(JNIEnv *env, jobject instance) {

return (*env)-> NewStringUTF(env, "secureapikeyfortheproject");
}