"use client";

import { Download } from "lucide-react";
import { trackEvent } from "@/lib/analytics";

export default function DownloadButton({ url }: { url: string }) {
  return (
    <a
      href={url}
      onClick={() => trackEvent("downloads")}
      className="flex items-center gap-2 px-3 py-1.5 text-xs font-bold bg-green-600 text-black rounded hover:bg-green-500 transition-all shadow-[0_0_15px_rgba(34,197,94,0.3)] hover:shadow-[0_0_25px_rgba(34,197,94,0.6)] cursor-pointer"
    >
      <Download className="w-4 h-4 md:w-3 md:h-3" />
      <span className="hidden md:inline">DOWNLOAD</span>
    </a>
  );
}