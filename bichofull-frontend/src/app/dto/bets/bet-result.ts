import { ResultDTO } from "./result-dto";

export class BetResult {
    id!: number;
    betAmount!: number;
    userName!: string;
    betType!: string;
    betNumber!: number;
    result!: ResultDTO;
    payout!: number;
    betDate!: string;
}
