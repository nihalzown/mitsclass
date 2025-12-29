"use client";

import { useEffect, useRef } from "react";
import { trackEvent } from "@/lib/analytics";

export default function AnalyticsTracker() {
  const ranOnce = useRef(false);

  useEffect(() => {
    // Prevent double-counting in React Strict Mode
    if (!ranOnce.current) {
      trackEvent("visits");
      ranOnce.current = true;
    }
  }, []);

  return null; // This component renders nothing visually
}