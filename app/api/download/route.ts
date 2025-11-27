import { NextRequest, NextResponse } from "next/server";
import fs from "fs";
import path from "path";

export async function GET(req: NextRequest) {
    const {searchParams} = new URL(req.url);
    const filePathParam = searchParams.get("file");

    if(!filePathParam){
        return NextResponse.json({error: "File path missing"},{status: 400});
    }

    const safePath = path.normalize(filePathParam).replace(/^(\.\.(\/|\\|$))+/, '');
    const fullPath = path.join(process.cwd(),"content",safePath);

    if(!fs.existsSync(fullPath)){
        return NextResponse.json({error: "File not found"},{status: 404});
    }

    const fileBuffer = fs.readFileSync(fullPath);
    const fileName = path.basename(fullPath);

    return new NextResponse(fileBuffer,{
        headers: {
            "Content-Type": "application/octet-stream",
            "Content-Disposition": `attachment; filename="${fileName}"`,
        },
    });
}