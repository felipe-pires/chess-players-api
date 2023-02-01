package com.chess.players.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chess.players.model.ChartByPlayer;
import com.chess.players.model.EventsByPlace;
import com.chess.players.model.Player;
import com.chess.players.model.RecordsByPlayer;
import com.chess.players.model.TopHundred;
import com.chess.players.service.PlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
 
@RestController
@RequestMapping("/player")
@Tag(name = "Player", description = "player controller")
public class PlayerController {

        @Autowired
        private PlayerService service;

        @GetMapping("/top-hundred")
        @Operation(summary = "Get Top 100", description = "Buscar o top 100 jogadores de algum rank", tags = {"TopHundred"})
        public ResponseEntity<List<TopHundred>> findTop100Players(@RequestParam String rank) throws Exception {
                return ResponseEntity.ok().body(service.findTop100Players(rank));
        }

        @GetMapping("/{fideId}")
        @Operation(summary = "Get player", description = "Buscar jogador por fideId", tags = {"Player"})
        public ResponseEntity<Player> findPlayer(@PathVariable String fideId) throws Exception {
                return ResponseEntity.ok().body(service.findPlayer(fideId));
        }

        @GetMapping("/{fideId}/chart")
        @Operation(summary = "Get Chart Player", description = "Buscar os dados do grafico de progresso do jogador", tags = {"ChartByPlayer"})
        public ResponseEntity<ChartByPlayer> findChartPlayer(@PathVariable String fideId) throws Exception {
                return ResponseEntity.ok().body(service.findChartPlayer(fideId));
        }

        @GetMapping("/{fideId}/top-records")
        @Operation(summary = "Get Top Records", description = "Buscar records de jogador por fideId", tags = {"RecordsByPlayer"})
        public ResponseEntity<RecordsByPlayer> findTopRecords(@PathVariable String fideId) throws Exception {
                return ResponseEntity.ok().body(service.findTopRecords(fideId));
        }

        @GetMapping("/events")
        @Operation(summary = "Get Events", description = "Buscar os eventos da fide", tags = {"EventsByPlace"})
        public ResponseEntity<List<EventsByPlace>> findEvents() throws Exception {
                return ResponseEntity.ok().body(service.findEvents());
        }
}
