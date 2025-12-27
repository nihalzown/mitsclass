import { NextResponse } from "next/server";
import fs from "fs";
import path from "path";
import { title } from "process";
import { Subtitles } from "lucide-react";

function extractAim(content: string): string | null {
    const lines = content.split('\n').slice(0, 15);
    for (const line of lines) {
        if (line.match(/aim\s*:/i) || line.match(/objective\s*:/i) || line.match(/program\s*to/i)) {
            return line
                .replace(/(\/\/|#|\/\*|\*)/g, '')
                .replace(/aim\s*:/i, '')
                .replace(/objective\s*:/i, '')
                .trim();
        }
    }
    return null;
}


export async function GET() {
    const contentdir = path.join(process.cwd(), "content");
    const results: any[] = [];
    if (!fs.existsSync(contentdir)) return NextResponse.json([]);

    const semesters = fs.readdirSync(contentdir).filter(f => !f.startsWith('.'));

    semesters.forEach(semester => {
        const semPath = path.join(contentdir, semester);
        if (!fs.lstatSync(semPath).isDirectory()) return;

        const subjects = fs.readdirSync(semPath).filter(f => !f.startsWith('.'));
        subjects.forEach(subject => {
            const subPath = path.join(semPath, subject);
            if (!fs.lstatSync(subPath).isDirectory()) return;

            const files = fs.readdirSync(subPath).filter(f => !f.startsWith('.'));
            files.forEach(file => {
                const filePath = path.join(subPath, file);
                if (fs.lstatSync(filePath).isDirectory()) return;

                let aim = null;
                try {
                    const content = fs.readFileSync(filePath, 'utf-8');
                    aim = extractAim(content);
                } catch (e) { }

                results.push({
                    id: `${semester}-${subject}-${file}`,
                    title: file,
                    Subtitle: aim || `Experiment in ${subject}`,
                    semester,
                    subject,
                    url: `/${semester}/${subject}/${file}`
                });
            });
        });
    });
    return NextResponse.json(results);
}
