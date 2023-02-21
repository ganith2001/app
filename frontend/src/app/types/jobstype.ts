import { Iskills } from "./requiredskills"

export interface Ialljobs{
    
        job_id: String,
        job_role: String,
        ctc: number,
        location: String,
        apply_by: String,
        cgpa: number,
        experiance: number,
        description: String,
        requiredSkills: Array<Iskills>
    
}