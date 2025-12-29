# CYBER VAULT // AC CODEX

![Project Banner](https://img.shields.io/badge/STATUS-OPERATIONAL-success?style=for-the-badge)
![Next.js](https://img.shields.io/badge/next.js-000000?style=for-the-badge&logo=nextdotjs&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/tailwindcss-%2338B2AC.svg?style=for-the-badge&logo=tailwind-css&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)

> **"Secure Repository for KTU Lab Records."**

**Cyber Vault** is a high-performance, stealth-themed academic repository built to store, view, and distribute laboratory source codes. It features a unique cyberpunk interface, real-time traffic analytics, and a seamless mobile experience—all running on a static, serverless architecture.

---

## 🔗 System Access

### 🟢 [Launch Live Terminal (cybervault.ac-inc.in)](https://cybervault.ac-inc.in/)
### 📂 [View Source Code (GitHub)](https://github.com/nihalzown/mitsclass)

---

## ⚡ Key Protocols (Features)

* **🕵️ Stealth UI:** A fully immersive, dark-mode cyberpunk interface with "hacker-style" typography and animations.
* **📂 Deep Navigation:** Hierarchical routing system (`Semester` -> `Subject` -> `Code File`).
* **💻 Syntax Intelligence:** Integrated code viewer with syntax highlighting (Prism.js) for C, Python, Java, and more.
* **📲 Mobile Optimized:** Responsive layout that adapts seamlessly from desktop command centers to mobile devices.
* **📊 Live Analytics:** Real-time tracking of visits and engagement using a custom API integration.
* **⚡ Instant Download:** Smart download system for quick extraction of lab records.

---

## 🛠️ System Architecture (Tech Stack)

* **Core:** Next.js 14 (App Router)
* **Language:** TypeScript
* **Styling:** Tailwind CSS + Lucide React Icons
* **Syntax Highlighting:** `react-syntax-highlighter`
* **Analytics:** Custom integration with `countapi.xyz`
* **Deployment:** Vercel Edge Network

---

## 🚀 Installation Protocol

To deploy this vault on your local machine:

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/nihalzown/mitsclass.git](https://github.com/nihalzown/mitsclass.git)
    cd mitsclass
    ```

2.  **Install Dependencies:**
    ```bash
    npm install
    # or
    yarn install
    ```

3.  **Initialize Development Server:**
    ```bash
    npm run dev
    ```

4.  **Access:**
    Open `http://localhost:3000` in your browser.

---

## 📂 Project Structure

```bash
├── app/
│   ├── [semester]/       # Dynamic Routing for Semesters
│   ├── api/download/     # File Download Logic
│   └── page.tsx          # Home Interface (Landing)
├── components/           # Reusable UI (Buttons, Search, Trackers)
├── lib/
│   ├── lab-data.ts       # Main Data Store (Experiment Codes)
│   └── analytics.ts      # API Logic for Traffic Counting
└── public/               # Static Assets

## 👨‍💻 Developer Signature

**Architect:** [Nihalzown](https://github.com/nihalzown)  
**Organization:** [AC Inc.](https://ac-inc.in/)  
**Version:** v1.1 [STABLE]