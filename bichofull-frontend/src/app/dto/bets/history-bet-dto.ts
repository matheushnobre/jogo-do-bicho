import { BetResult } from "./bet-result";

export interface HistoryBetDTO {
    bets: BetResult[];
    totalElements: number;
    totalPages: number;
}