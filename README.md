# Installation
<h3>Android Gradle</h3>
Add the dependency to your project's top-level build.gradle:

<div class="highlight highlight-source-groovy"><pre></span>
allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url 'https://oss.sonatype.org/content/repositories/snapshots'
        }
    }
}
</pre></div>

Then add the plugin to your app-level build.gradle:

<div class="highlight highlight-source-groovy"><pre></span>
dependencies {
    //session client
    implementation 'com.speechpro.android:session-client:0.9.8-SNAPSHOT'
}
</pre></div>

# How to use

<p>You can <a href="https://onepass.tech/vksession/help/" rel="nofollow">find the API documentation here</a>.</p>


<div class="highlight highlight-source-groovy"><pre></span>
final SessionClientFactory.SessionClient client = SessionClientFactory.get("https://onepass.tech/vksession/rest/", true);
new Thread(new Runnable() {
    public void run() {
        try {
            String result = client.openSession("vk_user", "123", 201);
        } catch (RestException e) {
            e.printStackTrace();
        } catch (InternetConnectionException e) {
            e.printStackTrace();
        }
    }
}).start();        
</pre></div>
