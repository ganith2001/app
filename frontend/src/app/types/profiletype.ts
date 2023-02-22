import { Icollegedetails } from "./collegeDetailsType"
import { ICSkills } from "./candidateskills"
import { Icandidate } from "./candidate"

export interface Iprofiledetails{
    phone_no:String,
    address:String,
    experience:number,
    cSignup:Icandidate,
    candidateSkills:Array<ICSkills>,
    candidateCollegeDetail:Array<Icollegedetails>
}