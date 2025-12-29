const NAMESPACE = "ac-codex-mits-prod"; 

type EventType = "visits" | "downloads";

export async function trackEvent(type: EventType) {
  try {
    const res = await fetch(`https://api.countapi.xyz/hit/${NAMESPACE}/${type}`);
    const data = await res.json();
    return data.value;
  } catch (error) {
    console.error(`[ANALYTICS] Error tracking ${type}:`, error);
    return 0;
  }
}

async function getCount(type: EventType) {
  try {
    const res = await fetch(`https://api.countapi.xyz/get/${NAMESPACE}/${type}`);
    const data = await res.json();
    return data.value;
  } catch (error) {
    return 0;
  }
}

export async function getAllStats() {
  const [visits, downloads] = await Promise.all([
    getCount("visits"),
    getCount("downloads")
  ]);
  return { visits, downloads };
}