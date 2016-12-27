##--------------- Default Settings  ----------
-optimizationpasses 10
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-dontnote
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

-keep public class **.R

-dontwarn android.**
-dontnote android.**
-keep class android.** { *; }

##---------------End: Default Settings  ----------

## NKAnimation
-dontwarn info.nukoneko.android.lib.nkanim.**
-dontnote info.nukoneko.android.lib.nkanim.**
-keep class info.nukoneko.android.lib.nkanim.** { *; }
