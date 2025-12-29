const NAMESPACE = "ac-codex-mits-prod-v1"; // Unique ID for your app

// Define what we are tracking
type EventType = "visits" | "downloads";

// 1. TRACK EVENT (Increment count)
export async function trackEvent(type: EventType) {
    try {
        const res = await fetch(`https://api.countapi.xyz/hit/${NAMESPACE}/${type}`);
        const data = await res.json();
        return data.value;
    } catch (error) {
        console.error(`Error tracking ${type}:`, error);
        return 0;
    }
}

// 2. GET STATS (Read count)
async function getCount(type: EventType) {
    try {
        const res = await fetch(`https://api.countapi.xyz/get/${NAMESPACE}/${type}`);
        const data = await res.json();
        return data.value;
    } catch (error) {
        return 0;
    }
}

// 3. GET ALL DATA (For Admin Dashboard)
export async function getAllStats() {
    const [visits, downloads] = await Promise.all([
        getCount("visits"),
        getCount("downloads")
    ]);
    return { visits, downloads };
}