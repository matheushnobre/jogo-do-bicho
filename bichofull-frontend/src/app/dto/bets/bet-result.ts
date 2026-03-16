import { ResultDTO } from "./result-dto";

export class BetResult {
    userName!: String;
    betType!: String;
    betNumber!: number;
    result!: ResultDTO;
    payout!: number;
}
