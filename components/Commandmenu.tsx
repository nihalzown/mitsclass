"use client";

import { useEffect, useState } from "react";
import { Command } from "cmdk";
import { Search, FileCode, ArrowRight } from "lucide-react";
import { useRouter } from "next/navigation";

type SearchResult = {
    id: string;
    title: string;
    subtitle: string;
    url: string;
    semester: string;
    subject: string;
};

export default function Commandmenu() {
    const [open, setOpen] = useState(false);
    const [items, setItems] = useState<SearchResult[]>([]);
    const router = useRouter();

    useEffect(() => {
        const down = (e: KeyboardEvent) => {
            if (e.key === "k" && (e.metaKey || e.ctrlKey)) {
                e.preventDefault();
                setOpen((open) => !open);
            }
        };
        document.addEventListener("keydown", down);

        fetch("/api/search")
            .then((res) => res.json())
            .then((data) => setItems(data));

        return () => document.removeEventListener("keydown", down);

    }, []);

    return (
        <Command.Dialog
            open={open}
            onOpenChange={setOpen}
            label="Global Search"
            className="fixed inset-0 z-[99] bg-black/80 backdrop-blur-sm flex items-center justify-center p-4"
            onClick={(e) => {
                if (e.target === e.currentTarget) setOpen(false);
            }}
        >
            <div className="w-full max-w-2xl bg-[#0a0a0a] border border-slate-800 rounded-xl shadow-2xl overflow-hidden animate-in fade-in zoom-in-95 duration-200">
                <div className="flex items-center border-b border-slate-800 px-4">
                    <Search className="w-5 h-5 text-slate-500 mr-3" />
                    <Command.Input
                        placeholder="Search files (e.g., 'Stack', 'Python', 'TCP')..."
                        className="w-full bg-transparent py-4 text-lg text-white placeholder:text-slate-600 focus:outline-none font-mono"
                    />
                    <div className="hidden md:flex gap-1">
                        <kbd className="px-2 py-1 bg-slate-900 border border-slate-800 rounded text-[10px] text-slate-500 font-mono">ESC</kbd>
                    </div>
                </div>
                <Command.List className="max-h-[60vh] overflow-y-auto p-2">
                    <Command.Empty className="py-6 text-center text-slate-500 font-mono text-sm">
                        No results found.
                    </Command.Empty>

                    {items.map((item) => (
                    <Command.Item
                        key={item.id}
                        value={`${item.title} ${item.subtitle} ${item.subject}`}
                        onSelect={() =>{
                            setOpen(false);
                            router.push(item.url);
                        }}
                        className="flex items-center justify-between p-3 rounded-lg cursor-pointer hover:bg-slate-900 group data-[selected=true]:bg-green-900/20 data-[selected=true]:border-green-500/30 border border-transparent transition-all">
                            <div className="flex items-center gap-4">
                                <FileCode className="w-4 h-4 text-slate-400 group-hover:text-green-500" />
                                <div className="flex flex-col">
                                    <span className="text-slate-200 font-bold text-sm group-hover:text-white">
                                        {item.title}
                                    </span>
                                    <span className="text-slate-500 text-xs line-clamp-1">
                                        {item.subtitle}
                                    </span>
                                </div>
                            </div>
                             <div className="flex items-center gap-3">
                                <span className="text-xs font-mono text-slate-600 uppercase">
                                    {item.semester} / {item.subject}
                                </span>
                                <ArrowRight className="w-4 h-4 text-slate-700 group-hover:text-green-500 opacity-0 group-hover:opacity-100 transition-all" />
                             </div>
                        </Command.Item>
                    ))}
                </Command.List>
            </div>
        </Command.Dialog>
    );
}