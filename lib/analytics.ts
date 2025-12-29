const NAMESPACE = "ac-codex-mits-prod"; // Unique Project ID

// Define the types of events we track
type EventType = "visits" | "downloads";

// Track an event (Increment count)
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

// Get count for a specific event
export async function getCount(type: EventType) {
  try {
    const res = await fetch(`https://api.countapi.xyz/get/${NAMESPACE}/${type}`);
    const data = await res.json();
    return data.value;
  } catch (error) {
    return 0;
  }
}

// Get ALL stats at once
export async function getAllStats() {
  const [visits, downloads] = await Promise.all([
    getCount("visits"),
    getCount("downloads")
  ]);
  return { visits, downloads };
}