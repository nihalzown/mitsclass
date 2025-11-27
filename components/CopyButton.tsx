"use client";
import { useState } from "react";
import { Copy, Check } from "lucide-react";

export default function CopyButton({ code }: { code: string }){
    const [copied, setCopied] = useState(false);

    const handleCopy =() =>{
        navigator.clipboard.writeText(code);
        setCopied(true);
        setTimeout(() => setCopied(false), 2000);
    };

    return (
        <button
            onClick={handleCopy}
            className="flex items-center gap-2 px-3 py-1.5 text-xs font-bold bg-slate-800 text-slate-300 border border-slate-700 rounded hover:bg-green-600 hover:text-black hover:border-green-500 transition-all"
        >
            {copied ? <Check className="w-3 h-3" /> : <Copy className="w-3 h-3" />}
            {copied ? "COPIED!" : "COPY RAW"}
        </button>
    );
}