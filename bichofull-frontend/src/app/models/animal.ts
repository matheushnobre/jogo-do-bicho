export class Animal {
    id!: number;
    name!: string;

    public getImage(){
        const id = this.id.toString().padStart(2, '0');
        return "/assets/animals/" + id + ".png";
    }
}