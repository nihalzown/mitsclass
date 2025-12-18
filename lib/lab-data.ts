import fs from "fs";
import path from "path";

const contentDirectory = path.join(process.cwd(), "content");
const isDirectory = (source: string) => fs.lstatSync(source).isDirectory();

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

export function getSems() {
    if (!fs.existsSync(contentDirectory)) return [];
    return fs.readdirSync(contentDirectory).filter(name => isDirectory(path.join(contentDirectory, name))).sort();
}

export function getSubs(semester: string) {
    const semPath = path.join(contentDirectory, semester);
    if (!fs.existsSync(semPath)) return [];
    return fs.readdirSync(semPath).filter(name => isDirectory(path.join(semPath, name)));
}

export function getExps(semester: string, subject: string) {
    const subjectPath = path.join(contentDirectory, semester, subject);

    if (!fs.existsSync(subjectPath)) return [];

    return fs.readdirSync(subjectPath)
        .filter(name => !name.startsWith("."))
        .map(file => {
            const filePath = path.join(subjectPath, file);

            // Try to read the file and extract aim
            let aim = null;
            try {
                const content = fs.readFileSync(filePath, "utf8");
                aim = extractAim(content);
            } catch (e) {
                console.error(`Failed to read header for ${file}`);
            }

            return {
                name: file,
                path: `${semester}/${subject}/${file}`,
                aim: aim // <--- This is the new data we send to the frontend
            };
        });
}

export function getCodes(semester: string, subject: string, filename: string) {
    const filePath = path.join(contentDirectory, semester, subject, filename);
    return fs.readFileSync(filePath, "utf8");
}