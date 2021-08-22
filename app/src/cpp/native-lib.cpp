#include <jni.h>
#include <string>
#include <jni.h>
#include <jni.h>
#include <jni.h>

extern "C"
JNIEXPORT jstring
Java_com_example_rokomaritest_model_utils_ShareInfo_getBaseUrl(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("http://202.84.44.253:5005/api/v1/");
}