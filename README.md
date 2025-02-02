<h1 align="center">📢 MyAdsApp - Simple Ad Display Application</h1>

<p align="center">
  <img src="https://your-image-link-here.jpg" width="600" alt="MyAdsApp Screenshot">
</p>

---

## 📌 Project Overview

This project is a **simple Android application** that fetches advertisements from a Flask-based backend, displays them in a loop, and tracks views and clicks.  

It was built as part of a **learning experiment** to develop a full-stack ad-serving system, from backend to frontend, including a reusable Android library.

---

## 🛠️ What I Did  

### 1️⃣ **Setting Up the Flask Server**
- Created a **Flask backend** to store ad data in **MongoDB Atlas**.
- Repository: 🔗 [FlaskAds_Server](https://github.com/yohaisim/FlaskAds_Server)
  
### 2️⃣ **Testing API Requests**
- Used **Postman** to send **POST/GET requests** to manage ads.  
- Experimented with adding, updating, and deleting ads.  

### 3️⃣ **Building an Admin Web Interface**
- Developed a **web-based control panel** for managing ads.  
- Features:
  ✅ **Add/Delete ads**  
  ✅ **Track views and clicks**  
  ✅ **Monitor ad performance**  

<p align="center">
  <img src="https://your-image-link-here.jpg" width="600" alt="Ad Management Panel">
</p>

### 4️⃣ **Developing the Android App**
- Built an Android app to:
  ✅ Fetch ads from the **Flask server**  
  ✅ Display them with a **10-second rotation**  
  ✅ Track views & clicks  

- Deployed the Flask backend to **Vercel** for public access.

### 5️⃣ **Creating a Reusable Android Library**
- Converted the ad-fetching logic into an **Android library**.
- Published the library via **JitPack**.

### 6️⃣ **Building a New App Using the Library**
- Created a **new Android app** that imports the library and displays ads.

<p align="center">
  <img src="https://your-image-link-here.jpg" width="600" alt="App Running Ads">
</p>

---

## 🏗️ Technologies Used  

<table align="center">
  <tr>
    <td align="center"><strong>Backend</strong></td>
    <td align="center"><strong>Frontend</strong></td>
    <td align="center"><strong>Android</strong></td>
  </tr>
  <tr>
    <td>⚡ Flask (Python)</td>
    <td>🌍 HTML/CSS + JavaScript</td>
    <td>📱 Android Studio (Java)</td>
  </tr>
  <tr>
    <td>🛢 MongoDB Atlas</td>
    <td>📊 Postman (API Testing)</td>
    <td>🔄 Retrofit + Picasso</td>
  </tr>
  <tr>
    <td>☁️ Vercel (Deployment)</td>
    <td>📊 Web Ad Management Panel</td>
    <td>📦 JitPack (Library Distribution)</td>
  </tr>
</table>

---

## 🚀 How to Run  

### 1️⃣ **Clone This Repository**
```bash
git clone https://github.com/yohaisim/MyAdsApp.git
cd MyAdsApp
