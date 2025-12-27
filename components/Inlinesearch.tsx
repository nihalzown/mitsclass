"use client";

import { useState, useEffect } from "react";
import { Command } from "cmdk";
import { Search, FileCode, ArrowRight, Loader2, Command as CommandIcon } from "lucide-react";
import { useRouter } from "next/navigation";

type SearchResult = {
  id: string;
  title: string;
  subtitle: string;
  url: string;
  semester: string;
  subject: string;
};

export default function InlineSearch() {
  const [items, setItems] = useState<SearchResult[]>([]);
  const [loading, setLoading] = useState(true);
  const [open, setOpen] = useState(false);
  const router = useRouter();

  useEffect(() => {
    fetch("/api/search")
      .then((res) => res.json())
      .then((data) => {
        setItems(data);
        setLoading(false);
      });
  }, []);

  return (
    <div className="w-full max-w-xl mx-auto relative z-50 font-sans group">
      
      {/* Container */}
      <Command
        shouldFilter={true}
        className="relative"
      >
        {/* --- THE SLEEK INPUT BAR --- */}
        <div 
          className={`
            flex items-center px-5 py-4 
            bg-[#0a0a0a]/60 backdrop-blur-xl 
            border rounded-2xl 
            transition-all duration-500 ease-out
            shadow-[0_0_0_1px_rgba(0,0,0,0)]
            
            ${open 
              ? "border-green-500/50 shadow-[0_0_40px_-10px_rgba(34,197,94,0.2)] bg-black" 
              : "border-white/10 hover:border-white/20 hover:bg-[#0a0a0a]/80"
            }
          `}
        >
          {/* Animated Search Icon */}
          <Search 
            className={`
              w-5 h-5 mr-4 transition-all duration-500
              ${open ? "text-green-400 scale-110" : "text-slate-500 group-hover:text-slate-400"}
            `} 
          />
          
          <Command.Input
            onValueChange={(val) => setOpen(!!val)} 
            onFocus={() => setOpen(true)}
            onBlur={() => setTimeout(() => setOpen(false), 200)}
            placeholder="Search protocol (e.g. Stack)..."
            className="w-full bg-transparent text-lg text-white placeholder:text-slate-600 focus:outline-none font-medium tracking-wide"
          />
          
          {/* Tech Badge for Loading / Shortcut */}
          <div className="flex items-center">
            {loading ? (
              <Loader2 className="w-4 h-4 text-green-500/50 animate-spin" />
            ) : (
              <div 
                className={`
                  hidden md:flex items-center gap-1.5 px-2 py-1 rounded-md border text-[10px] font-mono font-bold tracking-wider transition-all duration-300
                  ${open 
                    ? "border-green-500/20 bg-green-500/10 text-green-400" 
                    : "border-white/5 bg-white/5 text-slate-600"
                  }
                `}
              >
                 <CommandIcon className="w-3 h-3" />
                 <span>K</span>
              </div>
            )}
          </div>
        </div>

        {/* --- THE DROPDOWN RESULTS --- */}
        <div 
            className={`
                absolute top-full left-0 right-0 mt-4 
                bg-[#050505] border border-white/10 rounded-2xl 
                shadow-2xl overflow-hidden 
                transition-all duration-300 ease-out origin-top
                ${open && items.length > 0 ? "opacity-100 translate-y-0 scale-100" : "opacity-0 -translate-y-4 scale-95 pointer-events-none"}
            `}
        >
            <Command.List className="max-h-[320px] overflow-y-auto p-2 scrollbar-hide">
              <Command.Empty className="py-12 text-center">
                <p className="text-slate-600 font-mono text-xs uppercase tracking-widest">No signals found</p>
              </Command.Empty>

              {items.map((item) => (
                <Command.Item
                  key={item.id}
                  value={`${item.title} ${item.subtitle} ${item.subject}`}
                  onSelect={() => router.push(item.url)}
                  className="group/item flex items-center justify-between p-3 rounded-xl cursor-pointer aria-selected:bg-white/5 transition-all duration-200"
                >
                  <div className="flex items-center gap-4">
                    {/* Icon Box */}
                    <div className="flex items-center justify-center w-10 h-10 rounded-lg bg-white/5 border border-white/5 group-aria-selected/item:border-green-500/30 group-aria-selected/item:bg-green-500/10 transition-colors">
                      <FileCode className="w-5 h-5 text-slate-500 group-aria-selected/item:text-green-400 transition-colors" />
                    </div>
                    
                    <div>
                      <div className="text-sm font-semibold text-slate-200 group-aria-selected/item:text-white mb-0.5">
                        {item.title}
                      </div>
                      <div className="text-[11px] text-slate-500 font-mono line-clamp-1 group-aria-selected/item:text-slate-400">
                        {item.subtitle}
                      </div>
                    </div>
                  </div>
                  
                  <div className="flex items-center gap-3">
                     <span className="text-[10px] font-bold text-slate-700 bg-black border border-slate-800 px-2 py-1 rounded uppercase tracking-wider group-aria-selected/item:border-green-500/30 group-aria-selected/item:text-green-600 transition-colors">
                        {item.subject}
                     </span>
                     <ArrowRight className="w-4 h-4 text-slate-700 -translate-x-2 opacity-0 group-aria-selected/item:opacity-100 group-aria-selected/item:translate-x-0 group-aria-selected/item:text-white transition-all duration-300" />
                  </div>
                </Command.Item>
              ))}
            </Command.List>
            
            {/* Footer Status Bar */}
            <div className="px-4 py-2 bg-white/[0.02] border-t border-white/5 flex justify-between items-center">
               <span className="text-[10px] text-slate-600 font-mono">INDEX_SIZE: {items.length}</span>
               <span className="text-[10px] text-slate-600 font-mono flex items-center gap-1">
                  <span className="w-1.5 h-1.5 rounded-full bg-green-500 animate-pulse"/> LIVE
               </span>
            </div>
          </div>
      </Command>
    </div>
  );
}