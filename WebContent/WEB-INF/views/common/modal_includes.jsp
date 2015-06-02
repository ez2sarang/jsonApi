<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Modal -->
<div id="ziminModal01" class="modal fade" tabindex="-1" role="dialog" style="display: none;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
			</div> <!-- / .modal-body -->
		</div> <!-- / .modal-content -->
	</div> <!-- / .modal-dialog -->
</div> <!-- /.modal -->
<!-- / Modal -->

<!-- Modal -->
<div id="ziminModal02" class="modal fade" tabindex="-1" role="dialog" style="display: none;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
			</div> <!-- / .modal-body -->
		</div> <!-- / .modal-content -->
	</div> <!-- / .modal-dialog -->
</div> <!-- /.modal -->
<!-- / Modal -->

<!-- Modal -->
<div id="ziminModal03" class="modal fade" tabindex="-1" role="dialog" style="display: none;" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" onclick="$('#ziminModal03').modal('hide');" aria-hidden="true">×</button>
				<h4 class="modal-title" id="ziminModal03Label"></h4>
			</div>
			<div class="modal-body">
			</div> <!-- / .modal-body -->
		</div> <!-- / .modal-content -->
	</div> <!-- / .modal-dialog -->
</div> <!-- /.modal -->
<!-- / Modal -->

<!-- Success -->
<div id="zimin-modals-alerts-success" class="modal modal-alert modal-success fade" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<i class="fa fa-check-circle"></i>
			</div>
			<div class="modal-title">Some alert title</div>
			<div class="modal-body">Some alert text</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-success" data-dismiss="modal">OK</button>-->
				<button type="button" class="btn btn-success" onclick="$('#zimin-modals-alerts-success').modal('hide');">OK</button>
			</div>
		</div> <!-- / .modal-content -->
	</div> <!-- / .modal-dialog -->
</div> <!-- / .modal -->
<!-- / Success -->

<!-- Danger -->
<div id="zimin-modals-alerts-danger" class="modal modal-alert modal-danger fade" data-backdrop="static">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<i class="fa fa-times-circle"></i>
			</div>
			<div class="modal-title">Some alert title</div>
			<div class="modal-body">Some alert text</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" onclick="$('#zimin-modals-alerts-danger').modal('hide');">OK</button>
			</div>
		</div> <!-- / .modal-content -->
	</div> <!-- / .modal-dialog -->
</div> <!-- / .modal -->
<!-- / Danger -->

<!-- Warning -->
<div id="zimin-modals-alerts-warning" class="modal modal-alert modal-warning fade">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<i class="fa fa-warning"></i>
			</div>
			<div class="modal-title">Confirmation</div>
			<div class="modal-body">Some alert text</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" id="confirmFalse" data-dismiss="modal" onclick="$('#zimin-modals-alerts-warning .modal-body').html('Cancel')">Cancel</button>
				<button type="button" class="btn btn-warning" id="confirmTrue" data-dismiss="modal" onclick="$('#zimin-modals-alerts-warning .modal-body').html('OK')">OK</button>
			</div>
		</div> <!-- / .modal-content -->
	</div> <!-- / .modal-dialog -->
</div> <!-- / .modal -->
<!-- / Warning -->

<script type="text/javascript">
   	$('#ziminModal02').on('show.bs.modal', function() {
   		try {
   			$('#ziminModal01').css('opacity', .5);
   		} catch (e) {}
   	});
   	$('#ziminModal02').on('hidden.bs.modal', function() {
		try {
			$('#ziminModal01').css('opacity', 1);
   		} catch (e) {}
   	});
   	
   	$('#ziminModal03').on('show.bs.modal', function() {
   		try {
   			$('#ziminModal02').css('opacity', .5);
   		} catch (e) {}
   	});
   	$('#ziminModal03').on('hidden.bs.modal', function() {
		try {
			$('#ziminModal02').css('opacity', 1);
   		} catch (e) {}
   	});
</script>
