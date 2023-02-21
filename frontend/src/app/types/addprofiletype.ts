import { Icollegedetails } from "./collegeDetailsType"

export interface Iprofile{
    address:String,
    experience:number,
    phone_no:String,
    cid:String,
    skills:Array<String>,
    cDetails:Array<Icollegedetails>
}