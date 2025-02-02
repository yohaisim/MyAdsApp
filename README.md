<h1 align="center">📢 MyAdsApp - Simple Ad Display Application</h1>

<p align="center">
  <img src="https://i.postimg.cc/gkB83tH6/2025-02-02-122026.png" width="600" alt="MyAdsApp Screenshot">
</p>

<hr>

<h2>📌 Project Overview</h2>

<p>
This project is a <b>simple Android application</b> that fetches advertisements from a Flask-based backend, 
displays them in a loop, and tracks views and clicks.  
It was built as part of a <b>learning experiment</b> to develop a full-stack ad-serving system, from backend to frontend, including a reusable Android library.
</p>

<hr>

<h2>🛠️ What I Did</h2>

<h3>1️⃣ Setting Up the Flask Server</h3>
<ul>
  <li>Created a <b>Flask backend</b> to store ad data in <b>MongoDB Atlas</b>.</li>
  <li>Repository: <a href="https://github.com/yohaisim/FlaskAds_Server">FlaskAds_Server on GitHub</a></li>
  <li>Live server on <b>Vercel</b>: 🌍 <a href="https://vercel.com/yohais-projects-e8bb1029/flask-ads-server">Flask-Ads Server</a></li>
</ul>

<h3>2️⃣ Testing API Requests</h3>
<ul>
  <li>Used <b>Postman</b> to send <b>POST/GET requests</b> to manage ads.</li>
  <li>Experimented with adding, updating, and deleting ads.</li>
</ul>

<h3>3️⃣ Building an Admin Web Interface</h3>
<ul>
  <li>Developed a <b>web-based control panel</b> for managing ads.</li>
  <li>Features:</li>
  <ul>
    <li>✅ <b>Add/Delete ads</b></li>
    <li>✅ <b>Track views and clicks</b></li>
    <li>✅ <b>Monitor ad performance</b></li>
  </ul>
</ul>

<p align="center">
  <img src="https://i.postimg.cc/gkB83tH6/2025-02-02-122026.png" width="800" alt="Ad Management Panel">
</p>

<h3>4️⃣ Developing the Android App</h3>
<ul>
  <li>Built an Android app to:</li>
  <ul>
    <li>✅ Fetch ads from the <b>Flask server</b></li>
    <li>✅ Display them with a <b>10-second rotation</b></li>
    <li>✅ Track views & clicks</li>
  </ul>
</ul>

<h3>5️⃣ Creating a Reusable Android Library</h3>
<ul>
  <li>Converted the ad-fetching logic into an <b>Android library</b>.</li>
  <li>Published the library via <b>JitPack</b>.</li>
  <li>Library repository: 📦 <a href="https://github.com/yohaisim/AdsLibrary">AdsLibrary on GitHub</a></li>
  <li>Guide for using the library via <b>JitPack</b>:</li>
</ul>

<pre>
<code>
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.yohaisim:AdsLibrary:Tag'
}
</code>
</pre>

<h3>6️⃣ Building a New App Using the Library</h3>
<ul>
  <li>Created a <b>new Android app</b> that imports the library and displays ads.</li>
</ul>

<p align="center">
  <img src="https://i.postimg.cc/T3M7rP9b/Screenshot-20250202-122610.png" width="200" alt="App Running Ads">
</p>

<hr>

<h2>🚀 How to Run</h2>

<h3>1️⃣ Clone This Repository</h3>
<pre>
<code>
git clone https://github.com/yohaisim/MyAdsApp.git
cd MyAdsApp
</code>
</pre>

<h3>2️⃣ Open in Android Studio</h3>
<ul>
  <li>Click <b>File > Open</b>, then select the project folder.</li>
  <li>Wait for <b>Gradle Sync</b> to complete.</li>
</ul>

<h3>3️⃣ Ensure the Library Dependency is Correct</h3>
<pre>
<code>
dependencies {
    implementation 'com.github.yohaisim:AdsLibrary:1.0.6'
}
</code>
</pre>

<h3>4️⃣ Run the App</h3>
<ul>
  <li>Click <b>Run ▶</b> in Android Studio.</li>
  <li>The app will <b>fetch ads from the Flask server</b> and <b>display them in a loop</b>.</li>
</ul>

<hr>

<h2>📝 Example Code</h2>

<pre>
<code>
ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
Call<List<Ad>> call = apiService.getAds();

call.enqueue(new Callback<List<Ad>>() {
    @Override
    public void onResponse(Call<List<Ad>> call, Response<List<Ad>> response) {
        if (response.isSuccessful() && response.body() != null) {
            Ad ad = response.body().get(0);
            adTitle.setText(ad.getTitle());
            adDescription.setText(ad.getDescription());
            Picasso.get().load(ad.getImageUrl()).into(adImage);
        }
    }

    @Override
    public void onFailure(Call<List<Ad>> call, Throwable t) {
        Log.e("API_ERROR", "Error fetching ads", t);
    }
});
</code>
</pre>

<hr>

<h2>🎯 Summary</h2>

<ul>
  <li>✅ <b>Created a full-stack ad-serving system</b></li>
  <li>✅ <b>Built a Flask API + MongoDB for ad storage</b></li>
  <li>✅ <b>Developed a Web UI for ad management</b></li>
  <li>✅ <b>Built an Android app to fetch and display ads</b></li>
  <li>✅ <b>Packaged the ad-fetching logic as an Android library</b></li>
  <li>✅ <b>Published the library via JitPack</b></li>
  <li>✅ <b>Created a new app that imports and uses the library</b></li>
</ul>

<hr>

<h2>📜 License</h2>
<p>This project is <b>open-source</b> and free to use.</p>

<p>📩 <b>Contact:</b> yohaiasim@gmail.com</p>

<hr>

<h3 align="center">🎉 Project Complete! 🎉</h3>

<p align="center">Now everything is automated: the ads are stored, managed, and displayed dynamically in an app that imports a <b>reusable ad library</b>. 🚀</p>
